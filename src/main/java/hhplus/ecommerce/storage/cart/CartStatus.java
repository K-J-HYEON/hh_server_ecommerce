package hhplus.ecommerce.storage.cart;

public enum CartStatus {

    // 상태값 이거 아님
    READY("READY"),
    ADDED("ADDED"),
    DELETED("DELETED");

    private final String value;


    CartStatus(String value) {
        this.value = value;
    }
}
