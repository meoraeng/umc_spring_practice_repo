package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.enums.Gender;
import umc.study.domain.enums.SocialType;
import umc.study.domain.mapping.MemberAgree;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.MemberPrefer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity // 해당 클래스가 JPA의 엔티티임을 명시
@Getter
@Builder // 디자인 패턴으로 '빌더' 패턴을 사용하기 위함 -> 생성자를 사용하는 것 보다 더욱 편리하게 코딩 가능
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA가 통신을 하는 DBMS의 방식을 따른다.
    private  Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;


    @Enumerated(EnumType.STRING) // 기본 값인 ORDINAL을 사용하면 DB에 순서가 저장되고 순서가 바뀌면 에러 발생
    private Gender gender; // 정해진 값들 중 특정 값이 저장되는 경우 -> enum으로

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private LocalDateTime birth;

    private Long foodCategory;

    private String email;

    private String phoneNumber;

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();


    // 매핑 테이블도 따로 패키지를 모아서 관리
    //    private LocalDateTime createdAt; // 모든 엔터티에서 다 사용하는 경우 base 패키지
    //    private LocalDateTime updatedAt;
}
