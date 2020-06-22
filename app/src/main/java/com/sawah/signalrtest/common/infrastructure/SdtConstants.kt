package com.sawah.signalrtest.common.infrastructure


enum class AlertCategory(var value: Int) {
    ALL(-1),
    PERSON_OF_INTEREST(1),
    WANTED_VEHICLE(2),
    OVER_SPEEDING(3),
    SEAT_BELT(4),
    DISTRACTED_DRIVER(5),
    CAMERA(6),
    SYSTEM_HEALTH(7),
    AUDIT_TRAIL(8),
    BROADCASTS(9),
    ISRIMAP(10)
}

enum class SharedPrefsKey(var value: String) {
    RouteId("routeId"),
    LanguageBasedNationalId("languageBasedNationalId")

}

enum class SignalRStatus(var value: Int) {
    Disconnected(1),
    Connecting(0),
    Connected(2)
}
