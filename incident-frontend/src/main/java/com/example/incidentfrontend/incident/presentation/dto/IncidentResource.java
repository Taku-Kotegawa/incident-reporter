package com.example.incidentfrontend.incident.presentation.dto;

import com.example.incidentfrontend.base.domain.model.Status;
import com.example.incidentfrontend.incident.domain.model.IncidentLevel;
import com.example.incidentfrontend.incident.domain.model.IncidentStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
public class IncidentResource implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * ステータス
     */
    private Status status = Status.DRAFT;

    /**
     * タイトル
     */
    @NotNull
    @Size(max = 255)
    private String title;

    /**
     * 障害ステータス(障害発生(検知)/復旧作業/仮復旧/原因分析/対策実施/本復旧)
     */
    private IncidentStatus incidentStatus = IncidentStatus.CAUSE_ANALYSIS;

    /**
     * 障害レベル
     */
    private IncidentLevel incidentLevel = IncidentLevel.A;

    /**
     * 障害レベルの確定
     */
    private Boolean labelDecided = false;

    /**
     * 障害内容
     */
    private String content;

    /**
     * 影響範囲
     */
    private String impactRange;

    /**
     * 現在までの経緯
     */
    private String history;

    /**
     * 原因
     */
    private String cause;

    /**
     * 対応方法
     */
    private String countermeasures;

    /**
     * お客さまの状況
     */
    private String customerImpact;

    /**
     * その他(備考)
     */
    private String remark;

    /**
     * 添付ファイル
     */
    private Set<String> files;

    /**
     * To宛先
     */
    private Set<String> toAddress;

    /**
     * Cc宛先
     */
    private Set<String> ccAddress;

}
