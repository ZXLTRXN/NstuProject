package com.zxltrxn.nstuproject.features.parsing.plan

import com.zxltrxn.nstuproject.features.parsing.plan.data.model.DirectionData
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.FacultyData
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.FormTable
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.PlanData
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Direction
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Faculty
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Form
import com.zxltrxn.nstuproject.features.parsing.plan.domain.model.Plan

fun DirectionData.toDirection(): Direction {
    val convertToNullable: (String) -> String? = { value ->
        if (value == DirectionData.EMPTY) null else value
    }
    return Direction(
        name = name,
        code = code,
        level = level,
        budget = convertToNullable(budget),
        contract = convertToNullable(contract),
        quota = convertToNullable(quota),
        target = convertToNullable(target),
        competitiveGroup = competitiveGroup,
        accreditationPeriod = accreditationPeriod,
        special = convertToNullable(special)
    )
}


fun FacultyData.toFaculty(): Faculty =
    Faculty(name = name, directions = directions.map { it.toDirection() })

fun FormTable.toForm(): Form =
    Form(title = title, faculties = faculties.map { it.toFaculty() })

fun PlanData.toPlan(): Plan = Plan(title = title, forms = formTables.map { it.toForm() })