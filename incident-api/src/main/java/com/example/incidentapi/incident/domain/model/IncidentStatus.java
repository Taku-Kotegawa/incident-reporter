package com.example.incidentapi.incident.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.terasoluna.gfw.common.codelist.EnumCodeList;

/**
 * 障害のスタータス
 */
@AllArgsConstructor
@Getter
public enum IncidentStatus implements EnumCodeList.CodeListItem {

    OCCUR("障害発生(検知)"),
    RECOVERING("復旧作業"),
    TEMP_RECOVERY("仮復旧"),
    CAUSE_ANALYSIS("原因分析"),
    PROBLEM_SOLVING("対策実施"),
    COMPLETE("本復旧");

    private final String label;

    @Override
    public String getCodeLabel() {
        return label;
    }

    @Override
    public String getCodeValue() {
        return name();
    }
}
