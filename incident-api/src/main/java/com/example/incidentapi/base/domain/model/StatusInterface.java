package com.example.incidentapi.base.domain.model;

/**
 * ステータス管理を行うエンティティ用のインターフェイス
 */
public interface StatusInterface {

    /**
     * ステータスの設定
     *
     * @param status ステータス
     */
    void setStatus(Status status);

    /**
     * ステータスの取得
     *
     * @return ステータスのコード
     */
    Status getStatus();
}
