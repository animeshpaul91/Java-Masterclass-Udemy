package com.timbuchalka;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dev on 16/02/2016.
 */
public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public void addStock(StockItem item) {
        if(item != null) {
            // check if already have quantities of this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // If there are already stocks on this item, adjust the quantity
            if(inStock != item) {
                item.adjustStock(inStock.availableQuantity());
            }

            list.put(item.getName(), item);
            item.availableQuantity();
        }
    }

    public void sellStock(String item, int quantity) {
        StockItem inStock = list.get(item);

        if((inStock != null) && (quantity > 0)) {
            inStock.finaliseStock(quantity);
        }
        //        StockItem inStock = list.getOrDefault(item, null);
//
//        if((inStock != null) && (inStock.availableQuantity() >= quantity) && (quantity >0)) {
//            inStock.adjustStock(-quantity);
//            return quantity;
//        }
//        return 0;
    }

    public int reserveStock(String item, int quantity) {
        StockItem inStock = list.get(item);

        if((inStock != null) && (quantity > 0)) {
            return inStock.reserveStock(quantity);
        }
        return 0;
    }

    public int unreserveStock(String item, int quantity) {
        StockItem inStock = list.get(item);

        if((inStock != null) && (quantity > 0)) {
            return inStock.unreserveStock(quantity);
        }
        return 0;

    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for(Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\nStock List\n");
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.availableQuantity();

            s.append(stockItem).append(". There are ").append(stockItem.availableQuantity()).append(" in stock. Value of items: ");
            s.append(String.format("%.2f", itemValue)).append("\n");
            totalCost += itemValue;
        }

        return s + "Total stock value " + totalCost;
    }
}