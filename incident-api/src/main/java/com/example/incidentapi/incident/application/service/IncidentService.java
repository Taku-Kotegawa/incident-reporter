package com.example.incidentapi.incident.application.service;

import com.example.incidentapi.base.application.service.NodeIService;
import com.example.incidentapi.base.domain.model.message.MailSendHistory;
import com.example.incidentapi.incident.domain.model.Incident;

import java.util.List;

/**
 * 障害情報を取り扱うサービス
 */
public interface IncidentService extends NodeIService<Incident, Long> {

    /**
     * 障害報告メールを送信する。
     *
     * @param incident  報告メールの元となる障害情報
     * @param toAddress To送信先メールアドレスのリスト, null の場合はデフォルトの送信先が設定される。
     * @param ccAddress Cc送信先メールアドレスのリスト, null の場合はデフォルトの送信先が設定される。
     */
    void send(Incident incident, List<String> toAddress, List<String> ccAddress);

    /**
     * 障害報告メールの送信履歴を取得する
     *
     * @param id 障害情報エンティティのID
     * @return メール送信履歴のリスト
     */
    List<MailSendHistory> mailHistory(Long id);

    /**
     * 障害報告の変更履歴を取得する。
     *
     * @param id 障害情報エンティティのID
     * @return 変更履歴のリスト
     */
    List<Incident> history(Long id);



}
