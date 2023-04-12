package study.project.domain.address;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(zipCode, address1.zipCode)
                && Objects.equals(address, address1.address)
                && Objects.equals(detailAddress, address1.detailAddress)
                && Objects.equals(etc, address1.etc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, address, detailAddress, etc);
    }
}
