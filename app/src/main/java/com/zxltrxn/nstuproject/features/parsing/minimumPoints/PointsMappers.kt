package com.zxltrxn.nstuproject.features.parsing.minimumPoints

import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.PointsTable
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.SubjectData
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Points
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.PointsOnBase
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.Subject


fun SubjectData.toSubject(): Subject = Subject(name = name, points = points)

fun PointsTable.toPointsOnBase(): PointsOnBase =
    PointsOnBase(title = title, items = items.map { it.toSubject() })

fun PointsData.toPoints(): Points = Points(title = title, bases = tables.map { it.toPointsOnBase() })