package com.zxltrxn.nstuproject.features

import android.webkit.WebSettings
import com.zxltrxn.nstuproject.features.webView.ContentStyle

enum class Page(
    val url: String,
    val contentStyle: ContentStyle = ContentStyle(),
    val cacheMode: Int = WebSettings.LOAD_CACHE_ELSE_NETWORK
) {
    PHONE("https://nstu.ru/entrance/committee"), // готово
    COMPETITION("https://www.nstu.ru/entrance/committee/completition2020"), //старое
    RECRUITING_PLAN("https://nstu.ru/entrance/committee/plan"), // готово
    MINIMUM_POINTS("https://nstu.ru/entrance/committee/exav_min_22"), // готово
    SEARCH_BACHELORS_PROGRAMS(
        "https://nstu.ru/entrance/committee/search_direction",
        ContentStyle(stretchMinContent = true)
    ), // готово
    EDUCATIONAL_PLANS(
        "https://www.nstu.ru/studies/study/edu_plans",
        ContentStyle(stretchTable = true)
    ), // готово
    ENTRANCE_EXAMINATIONS(
        "https://www.nstu.ru/entrance/committee/prilogenie3",
        ContentStyle(stretchTable = true)
    ), // (с листалкой)
    ENTRANCE_EXAMINATIONS_SCHEDULE("https://www.nstu.ru/entrance/entrance_all/schedule"), //старое
    SPECIAL_RIGHTS_FOR_WINNERS("https://www.nstu.ru/entrance/committee/prilogenie4"), // готово
    INDIVIDUAL_ACHIEVEMENTS(
        "https://www.nstu.ru/entrance/committee/prilogenie5",
        ContentStyle(stretchTable = true),
    ), // (с листалкой)
    PERSONAL_AREA(
        "https://ciu.nstu.ru/enrollee_account/",
        ContentStyle(removeOddBlocks = false, removeOddBlocksCiu = true)
    ), // готово
    ACCEPTANCE_OF_DOCUMENTS("https://www.nstu.ru/entrance/committee/entrance_examination"), // готово
    CONTRACT("https://www.nstu.ru/entrance/committee/contract"), // готово
    COST(
        "https://www.nstu.ru/studies/cost_education/edu_cost",
        ContentStyle(stretchMinContent = true)
    ), // готово
    HOSTEL("https://www.nstu.ru/campus/hostel"), // готово
    GRANTS("https://www.nstu.ru/studies/study/scholarship"), // готово
    RATING_LIST(
        "https://www.nstu.ru/entrance/admission_campaign/entrance",
        ContentStyle(stretchMinContent = true),
        WebSettings.LOAD_NO_CACHE
    ), // готово
    INVALID("https://www.nstu.ru/studies/study/ZIS#escort"),
    QUESTIONS("https://www.nstu.ru/entrance/answers"), // готово
    POLYCLINIC("https://www.nstu.ru/campus/health/polyclinic"), // готово
    CULTURAL("https://www.nstu.ru/campus/culture/culture_centre"), // готово
    GARAGE("https://www.nstu.ru/campus/garage_point"), // готово
    PROPHYLAXIS("https://www.nstu.ru/campus/health/health_centre"), // готово
    SPORTS("https://www.nstu.ru/campus/sport/sports_groups"), // готово
    CAMPUS_CARD("https://www.nstu.ru/campus/Campus_card") // готово
}
// ciu.nstu.ru онлаин кабинет
