package org.example.external.exception

import org.example.external.event.*

class ShipmentDataProcessingException(m: String?, c: Throwable?, val event: ShipmentEvent?) : Exception(m, c) {
    constructor(c: Throwable?) : this(c?.toString(), c, null)

    constructor(message: String?) : this(message, null, null)

    constructor() : this(null, null, null)

    constructor(event: ShipmentEvent) : this(null, null, event)
}