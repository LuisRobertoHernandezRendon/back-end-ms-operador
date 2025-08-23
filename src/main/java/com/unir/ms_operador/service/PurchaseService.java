package com.unir.ms_operador.service;

import java.util.List;

import com.unir.ms_operador.controller.model.CreatePurchaseRequest;
import com.unir.ms_operador.data.model.Purchase;

public interface PurchaseService {

  List<Purchase> getPurchasesByUser(Long userId);

  Purchase createPurchase(CreatePurchaseRequest request);

}
