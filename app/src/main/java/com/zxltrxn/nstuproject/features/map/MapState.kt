package com.zxltrxn.nstuproject.features.map

import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.zxltrxn.nstuproject.features.Page

data class MapState(
    val uiSettings: MapUiSettings = MapUiSettings(zoomControlsEnabled = false),
    val properties: MapProperties = MapProperties(
        mapType = MapType.HYBRID,
        mapStyleOptions = MapStyleOptions(hideOrganizationsJson)
    ),
    val spots: List<Spot> = campusSpots
) {
    companion object {
        private val hideOrganizationsJson = """
                [
                    {
                        "featureType": "poi.business",
                        "elementType": "all",
                        "stylers":[
                            {
                            "visibility": "off"
                            }
                        ]
                    },
                    {
                        "featureType": "poi.government",
                        "elementType": "all",
                        "stylers":[
                            {
                            "visibility": "off"
                            }
                        ]
                    }
                ]
                """.trimIndent()

        private val campusSpots = listOf(
            Spot(
                type = Spot.Type.HOSTEL,
                num = 1,
                lat = 54.988619,
                lng = 82.904776,
                description = "Общежитие коридорного типа",
                inAppLink = Page.HOSTEL
            ),
            Spot(
                type = Spot.Type.HOSTEL,
                num = 2,
                lat = 54.987341,
                lng = 82.902464,
                description = "Общежитие коридорного типа",
                inAppLink = Page.HOSTEL
            ),
            Spot(
                type = Spot.Type.HOSTEL,
                num = 3,
                lat = 54.988248,
                lng = 82.900233,
                description = "Общежитие коридорного типа",
                inAppLink = Page.HOSTEL
            ),
            Spot(
                type = Spot.Type.HOSTEL,
                num = 4,
                lat = 54.98985,
                lng = 82.902822,
                description = "Общежитие коридорного типа",
                inAppLink = Page.HOSTEL
            ),
            Spot(
                type = Spot.Type.HOSTEL,
                num = 5,
                lat = 54.988904, lng = 82.900077,
                description = "Общежитие коридорного типа",
                inAppLink = Page.HOSTEL
            ),
            Spot(
                type = Spot.Type.HOSTEL,
                num = 6,
                lat = 54.990075,
                lng = 82.901336,
                description = "Общежитие секционного типа",
                inAppLink = Page.HOSTEL
            ),
            Spot(
                type = Spot.Type.HOSTEL,
                num = 7,
                lat = 54.987719,
                lng = 82.901024,
                description = "Общежитие секционного типа",
                inAppLink = Page.HOSTEL
            ),
            Spot(
                type = Spot.Type.HOSTEL,
                num = 8, lat = 54.988079,
                lng = 82.91578,
                description = "В общежитии для аспирантов, докторантов, преподавателей и сотрудников" +
                        " на каждом этаже по 42 комнаты с санузлом, душем и кухонным отсеком."
            ),

            Spot(
                type = Spot.Type.CAMPUS,
                num = 1,
                lat = 54.987815,
                lng = 82.906219,
                description = """
                    Здесь располагаются:
                    - Факультет прикладной математики и информатики
                    - Институт дистанционного обучения
                    - Факультет повышения квалификации
                    - Учебный центр «Институт Конфуция» 
                    - и т.д.
                """.trimIndent()
            ),
            Spot(
                type = Spot.Type.CAMPUS,
                num = 2,
                lat = 54.986752,
                lng = 82.905597,
                description = """
                    Здесь располагаются:
                    - Приемная комиссия (1 этаж)
                    - Факультет энергетики
                    - Факультет мехатроники и автоматизации
                    - и т.д.
                """.trimIndent()
            ),
            Spot(
                type = Spot.Type.CAMPUS,
                num = 3,
                lat = 54.986819, lng = 82.908343,
                description = null
            ),
            Spot(
                type = Spot.Type.CAMPUS,
                num = 4,
                lat = 54.985342, lng = 82.906705,
                description = null
            ),
            Spot(
                type = Spot.Type.CAMPUS,
                num = 5,
                lat = 54.986136, lng = 82.903559,
                description = null
            ),
            Spot(
                type = Spot.Type.CAMPUS,
                num = 6,
                lat = 54.98654, lng = 82.90366,
                description = null
            ),
            Spot(
                type = Spot.Type.CAMPUS,
                num = 7,
                lat = 54.986952, lng = 82.91506,
                description = null
            ),
            Spot(
                type = Spot.Type.CAMPUS,
                num = 8,
                lat = 54.986415, lng = 82.907011,
                description = null
            ),

            Spot(
                type = Spot.Type.LIBRARY,
                num = null,
                lat = 54.985987, lng = 82.906326,
                description = null,
                link = "https://library.nstu.ru/company/"
            ),
            Spot(
                type = Spot.Type.COMPLEX,
                num = null,
                lat = 54.988353, lng = 82.902478,
                description = null,
                link = "https://sport.nstu.ru/sports_halls/"
            ),
            Spot(
                type = Spot.Type.PALACE,
                num = null,
                lat = 54.988592, lng = 82.901319,
                description = null,
                link = "https://sport.nstu.ru/sports_halls/"
            ),
            Spot(
                type = Spot.Type.CULTURAL,
                num = null,
                lat = 54.989411, lng = 82.901128,
                description = null,
                inAppLink = Page.CULTURAL
            ),
            Spot(
                type = Spot.Type.INCUBATOR,
                num = null,
                lat = 54.989454, lng = 82.900297,
                description = null,
                inAppLink = Page.GARAGE
            ),
            Spot(
                type = Spot.Type.PROPHYLAXIS,
                num = null,
                lat = 54.987175, lng = 82.902054,
                description = null,
                inAppLink = Page.PROPHYLAXIS
            ),
            Spot(
                type = Spot.Type.POLYCLINIC,
                num = null,
                lat = 54.989001, lng = 82.900162,
                description = null,
                inAppLink = Page.POLYCLINIC
            ),
            Spot(
                type = Spot.Type.BOWLING,
                num = null,
                lat = 54.989486, lng = 82.900335,
                description = null,
                link = "https://sport.nstu.ru/sports_halls/bowling/"
            ),
            Spot(
                type = Spot.Type.SPORT_GROUND,
                num = null,
                lat = 54.988321, lng = 82.900859,
                description = null
            ),
            Spot(
                type = Spot.Type.SPORT_GROUND,
                num = null,
                lat = 54.989498, lng = 82.90196,
                description = null
            ),
        )
    }
}