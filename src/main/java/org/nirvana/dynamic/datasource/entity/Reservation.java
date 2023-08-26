package org.nirvana.dynamic.datasource.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Nirvana
 * @since 2023-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String tenantId;

    private Long appId;

    private Integer version;

    private String remark;

    private Integer isDeleted;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Long createdBy;

    private Long updatedBy;

    private Long memberId;

    private String memberPhone;

    private Long petId;

    private String petName;

    private Long petBreed;

    private Integer petClassId;

    private Long serviceId;

    private Integer petSex;

    private String petWeight;

    private Integer hairLengthType;

    private Integer status;

    private Integer payStatus;

    private Integer cancelType;

    private Integer channelSource;

    private LocalDateTime reservationBeginTime;

    private LocalDateTime reservationEndTime;

    private String orderNo;

    private LocalDateTime confirmTime;

    private LocalDateTime payTime;

    private LocalDateTime cancelTime;

    private LocalDateTime finishTime;

    private Long merchantId;

    private Long shopId;

    private Integer creatorType;

    private Integer updatedType;


}
