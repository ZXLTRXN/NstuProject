package com.zxltrxn.nstuproject.features

import android.webkit.WebSettings

enum class Page(
    val url: String,
    val stretchContent: Boolean = false,
    val cacheMode: Int = WebSettings.LOAD_CACHE_ELSE_NETWORK
) {
    PHONE("https://www.nstu.ru/entrance/committee"), // готово
    COMPETITION("https://www.nstu.ru/entrance/committee/completition2020"), //старое
    COMPETITION21("https://www.nstu.ru/entrance/admission_campaign/current_numbers"), // пусто
    RECRUITING_PLAN("https://www.nstu.ru/entrance/committee/plan"), // криво надо парсить                      !!!
    MINIMUM_POINTS("https://www.nstu.ru/entrance/committee/exav_min_22"), // готово
    SEARCH_BACHELORS_PROGRAMS("https://www.nstu.ru/entrance/committee/search_direction",true), // готово
    EDUCATIONAL_PLANS("https://www.nstu.ru/studies/study/edu_plans"), // готово
    ENTRANCE_EXAMINATIONS("https://www.nstu.ru/entrance/committee/prilogenie3"), // (с листалкой но неудобно)  !!!
    ENTRANCE_EXAMINATIONS_SCHEDULE("https://www.nstu.ru/entrance/entrance_all/schedule"), //старое
    SPECIAL_RIGHTS_FOR_WINNERS("https://www.nstu.ru/entrance/committee/prilogenie4"), // готово
    INDIVIDUAL_ACHIEVEMENTS("https://www.nstu.ru/entrance/committee/prilogenie5"), // (с листалкой)
    PERSONAL_AREA("https://ciu.nstu.ru/enrollee_account/"), // интент хедер                                    !!!
    ACCEPTANCE_OF_DOCUMENTS("https://www.nstu.ru/entrance/committee/entrance_examination"), // готово
    CONTRACT("https://www.nstu.ru/entrance/committee/contract"), // готово
    COST("https://www.nstu.ru/studies/cost_education/edu_cost", true), // готово
    HOSTEL("https://www.nstu.ru/campus/hostel"), // готово
    GRANTS("https://www.nstu.ru/studies/study/scholarship"), // готово
    RATING_LIST("https://www.nstu.ru/entrance/admission_campaign/entrance",true), // готово
    INVALID("https://www.nstu.ru/studies/study/ZIS#escort")
}
// ciu.nstu.ru онлаин кабинет