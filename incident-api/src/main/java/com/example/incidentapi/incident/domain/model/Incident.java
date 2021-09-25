package com.example.incidentapi.incident.domain.model;

import com.example.incidentapi.base.domain.model.AbstractEntity;
import com.example.incidentapi.base.domain.model.FileManaged;
import com.example.incidentapi.base.domain.model.Status;
import com.example.incidentapi.base.domain.model.StatusInterface;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * 障害情報
 */
@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Incident extends AbstractEntity<Long> implements Serializable, StatusInterface {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ステータス
     */
    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

    /**
     * タイトル
     */
    private String title;

    /**
     * 障害ステータス(障害発生(検知)/復旧作業/仮復旧/原因分析/対策実施/本復旧)
     */
    @Enumerated(EnumType.STRING)
    private IncidentStatus incidentStatus = IncidentStatus.CAUSE_ANALYSIS;

    /**
     * 障害レベル
     */
    @Enumerated(EnumType.STRING)
    private IncidentLevel incidentLevel = IncidentLevel.A;

    /**
     * 障害レベルの確定
     */
    private Boolean labelDecided = false;

    /**
     * 障害内容
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * 影響範囲
     */
    @Column(columnDefinition = "TEXT")
    private String impactRange;

    /**
     * 現在までの経緯
     */
    @Column(columnDefinition = "TEXT")
    private String history;

    /**
     * 原因
     */
    @Column(columnDefinition = "TEXT")
    private String cause;

    /**
     * 対応方法
     */
    @Column(columnDefinition = "TEXT")
    private String countermeasures;

    /**
     * お客さまの状況
     */
    @Column(columnDefinition = "TEXT")
    private String customerImpact;

    /**
     * その他(備考)
     */
    @Column(columnDefinition = "TEXT")
    private String remark;

    /**
     * 添付ファイル
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    private Set<String> files;

    /**
     * To宛先
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    private Set<String> toAddress;

    /**
     * Cc宛先
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn
    private Set<String> ccAddress;


}
