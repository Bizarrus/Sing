package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.Arrays;
import java.util.List;

public class PurchasesAPI$PurchaseRequest extends SnpRequest {
    public List<Order> orders;

    public static class Order {
        public String orderId;
        public String productId;
        public String productType = "CNPCK";
        public String receipt;

        public Order setProductId(String str) {
            this.productId = str;
            return this;
        }

        public Order setOrderId(String str) {
            this.orderId = str;
            return this;
        }

        public Order setReceipt(String str) {
            this.receipt = str;
            return this;
        }
    }

    public PurchasesAPI$PurchaseRequest setOrder(Order order) {
        if (order != null) {
            this.orders = Arrays.asList(new Order[]{order});
        }
        return this;
    }
}
