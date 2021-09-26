package com.example.incidentfrontend.incident.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.terasoluna.gfw.common.codelist.EnumCodeList;

/**
 * 障害レベル
 */
@AllArgsConstructor
@Getter
public enum IncidentLevel implements EnumCodeList.CodeListItem {

    A("Ａ"),
    B("Ｂ"),
    C("Ｃ"),
    O("不明");

    private final String label;

    @Override
    public String getCodeLabel() {
        return this.label;
    }

    @Override
    public String getCodeValue() {
        return name();
    }
}
