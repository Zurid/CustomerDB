package entities;

public class BaseEntity {

    public boolean identical(final Object object) {
        return true;
    }

    protected boolean eq(final Object o1, final Object o2) {
        boolean res = eq0(o1, o2) || eq0(o2, o1) || (o1 == null && o2 == null);
        System.out.printf("res: %b; val1 = %s; val2 = %s", res, o1, o2);
        return res;
    }

    private boolean eq0(final Object o1, final Object o2) {
        return o1 != null && o1.equals(o2);
    }
}
