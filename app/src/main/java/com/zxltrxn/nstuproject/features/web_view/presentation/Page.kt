package com.zxltrxn.nstuproject.features.web_view.presentation

enum class Page(val url:String) {
    PHONEBOOK("https://nstu.ru/phone"),  // check криво
    COMPETITION("https://www.nstu.ru/entrance/committee/completition2020"),// на каждый год направления заменить их названиями
    RECRUITING_PLAN("https://www.nstu.ru/entrance/committee/plan"), // check криво
    MINIMUM_POINTS("https://www.nstu.ru/entrance/admission_campaign/exam_min"), //parse но не обязательно
    SEARCH_BACHELORS_PROGRAMS("https://www.nstu.ru/entrance/committee/search_direction"), // не требует доработки
    EDUCATIONAL_PLANS("https://www.nstu.ru/studies/study/edu_plans"), //slow
    ENTRANCE_EXAMINATIONS("https://www.nstu.ru/entrance/committee/prilogenie3"), // не требует доработки
    ENTRANCE_EXAMINATIONS_SCHEDULE("https://www.nstu.ru/entrance/entrance_all/schedule"), //parse но не обязательно
    INDIVIDUAL_ACHIEVEMENTS("https://www.nstu.ru/entrance/committee/prilogenie5"), // не требует доработки
    PERSONAL_AREA("https://www.nstu.ru/entrance/enrollee_account"), // нужен интент на браузер
    ACCEPTANCE_OF_DOCUMENTS("https://www.nstu.ru/entrance/committee/entrance_examination"), // не требует доработки
    CONTRACT("https://www.nstu.ru/entrance/committee/contract") // не требует доработки
//    RAITING_LIST("https://www.nstu.ru/entrance/admission_campaign/entrance")// криво но нужно ли вообще
}
// ciu.nstu.ru онлаин кабинет