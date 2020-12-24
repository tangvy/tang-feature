package com.tangv.feature.model;

import com.tangv.feature.aspect.declare.IPojo;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 * Author:      TangWei
 * Date:        2020/9/12 9:53 下午
 */
@Data
public class BasePojo implements IPojo, Serializable {
    @Id
    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    @Override
    public void prepareInsert() {
        this.setCreateTime(LocalDateTime.now());
        this.setModifyTime(LocalDateTime.now());
    }

    @Override
    public void prepareUpdate() {
        this.setModifyTime(LocalDateTime.now());
    }
}
