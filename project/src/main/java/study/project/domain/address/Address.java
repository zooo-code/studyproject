package study.project.domain.address;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    private String zipCode;
    private String address;
    private String detailAddress;
    private String etc;

    protected Address() {
    }

    public Address(String zipCode, String address, String detailAddress, String etc) {
        this.zipCode = zipCode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.etc = etc;
    }
}
