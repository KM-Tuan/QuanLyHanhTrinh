/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import java.util.List;

/**
 *
 * @author kieum
 */
public interface FoodOrderItemRepository {
    List<Object[]> getMostOrderedByJourney();
}
