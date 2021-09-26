package com.example.incidentapi.incident.presentation.dto;

import com.example.incidentapi.base.domain.model.Status;
import com.example.incidentapi.incident.domain.model.IncidentLevel;
import com.example.incidentapi.incident.domain.model.IncidentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@Schema(name = "Incident", description = "障害情報")
public class IncidentResource implements Serializable {

    /**
     * id
     */
    @NotNull
    @Schema(description = "障害情報を一意に表す自動連番", example = "100")
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
