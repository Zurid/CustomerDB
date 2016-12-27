package entities.service;

import entities.Customer;
import entities.DiscountCode;
import entities.MicroMarket;
import java.io.Serializable;
import javax.persistence.EntityManager;

public class CustomerAdapter implements Serializable {

    public String customerId;
    public Cell name;
    public String addressline1;
    public String addressline2;
    public String city;
    public String creditLimit;
    public String discountCode;
    public String email;
    public String fax;
    public String phone;
    public String state;
    public String zip;
    public String updateStatus;
    public String updateError;

    public void initNewDataTo(final Customer c, final EntityManager em) {
        initOldDataTo(c, em);
        c.setName(name.newValue);
    }

    public void initOldDataTo(final Customer c, final EntityManager em) {
        c.setCustomerId(Integer.parseInt(customerId));
        c.setName(name.value);
        c.setAddressline1(addressline1);
        c.setAddressline2(addressline2);
        c.setCity(city);
        c.setCreditLimit(Integer.parseInt(creditLimit));
        c.setEmail(email);
        c.setFax(fax);
        c.setPhone(phone);
        c.setState(state);
        DiscountCode discountCodeEntity = em.find(DiscountCode.class, discountCode.charAt(0));
        c.setDiscountCode(discountCodeEntity);

        MicroMarket microMarket = em.find(MicroMarket.class, zip);
        c.setZip(microMarket);
    }

    public void initFrom(final Customer c) {
        customerId = c.getCustomerId().toString();
        name = new Cell();
        name.value = c.getName();
        addressline1 = c.getAddressline1();
        addressline2 = c.getAddressline2();
        city = c.getCity();
        creditLimit = c.getCreditLimit() + "";
        email = c.getEmail();
        fax = c.getFax();
        phone = c.getPhone();
        state = c.getState();
        DiscountCode dc = c.getDiscountCode();
        if (dc != null) {
            discountCode = dc.getDiscountCode() + "";
        }
        MicroMarket mm = c.getZip();
        if (mm != null) {
            zip = mm.getZipCode();
        }
    }
}
