package org.example.service

import kotlinx.coroutines.*
import org.jeasy.random.*
import org.slf4j.*
import org.springframework.cloud.stream.function.*
import java.util.concurrent.atomic.*
import kotlin.concurrent.*

abstract class AbstractRandomProducer<T>(
    private val streamBridge: StreamBridge,
    private val binderName: String,
    protected val easyRandom: EasyRandom,
) {
    private val counter = AtomicLong(0)
    private val logger = LoggerFactory.getLogger(AbstractRandomProducer::class.java)
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    init {
        fixedRateTimer("counter", false, 0, 1000) {
            logger.info("messages per sec: $counter")
            counter.set(0)
        }
    }

    protected abstract fun createRandomData(): T

    fun produceRandomData() {
        produce(createRandomData())
    }

    fun produce(event: T) {
        streamBridge.send(binderName, event)
    }

    fun startProducingRandomData(timeout: Long, count: Int) {
        stopProducingRandomData()
        repeat(count) {
            scope.launch {
                while (true) {
                    produceRandomData()
                    counter.incrementAndGet()
                    delay(timeout)
                }
            }
        }
    }

    fun stopProducingRandomData() {
        scope.coroutineContext.cancelChildren()
    }
}