package com.accolite.restfulkafka.customobject;

import java.time.*;

public record CustomMessage(String data, LocalDateTime created ) {

}
