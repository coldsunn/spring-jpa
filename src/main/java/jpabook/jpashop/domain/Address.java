package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable // 임베디드나 임베더블 둘 중 하나만 있어도 되긴 하는데 보통은 둘 다 함
@Getter // 값 타입은 불변 객체여야 한다. 생성할 때만 값이 세팅되고 setter는 제공X
public class Address {
    private String city;
    private String street;
    private String zipcode;
    //기본 생성자 필요함, 최대한 이거로 생성하면 안되니까 protected
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
