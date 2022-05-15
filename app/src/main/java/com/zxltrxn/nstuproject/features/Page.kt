package com.zxltrxn.nstuproject.features

enum class Page(val url: String) {
    PHONE("https://www.nstu.ru/entrance/committee"),
    COMPETITION("https://www.nstu.ru/entrance/committee/completition2020"), //старое
    COMPETITION21("https://www.nstu.ru/entrance/admission_campaign/current_numbers"), // пусто
    RECRUITING_PLAN("https://www.nstu.ru/entrance/committee/plan"), // криво
    MINIMUM_POINTS("https://www.nstu.ru/entrance/committee/exav_min_22"), //parsed
    SEARCH_BACHELORS_PROGRAMS("https://www.nstu.ru/entrance/committee/search_direction"), // криво
    EDUCATIONAL_PLANS("https://www.nstu.ru/studies/study/edu_plans"), //slow не требует доработки(с листалкой)
    ENTRANCE_EXAMINATIONS("https://www.nstu.ru/entrance/committee/prilogenie3"), // не требует доработки(с листалкой но неудобно)
    ENTRANCE_EXAMINATIONS_SCHEDULE("https://www.nstu.ru/entrance/entrance_all/schedule"), //старое
    SPECIAL_RIGHTS_FOR_WINNERS("https://www.nstu.ru/entrance/committee/prilogenie4"), // не требует доработки
    INDIVIDUAL_ACHIEVEMENTS("https://www.nstu.ru/entrance/committee/prilogenie5"), // не требует доработки(с листалкой)
    PERSONAL_AREA("https://ciu.nstu.ru/enrollee_account/"), // интент хедер!
    ACCEPTANCE_OF_DOCUMENTS("https://www.nstu.ru/entrance/committee/entrance_examination"), // не требует доработки
    CONTRACT("https://www.nstu.ru/entrance/committee/contract"), // не требует доработки
    COST("https://www.nstu.ru/studies/cost_education/edu_cost"), // криво
    HOSTEL("https://www.nstu.ru/campus/hostel"), // не требует доработки
    INVALID("https://www.nstu.ru/studies/study/ZIS#escort"),
    GRANTS("https://www.nstu.ru/studies/study/scholarship"),
    RAITING_LIST("https://www.nstu.ru/entrance/admission_campaign/entrance")// криво но нужно ли вообще для общего ознакомления да
}
// ciu.nstu.ru онлаин кабинет