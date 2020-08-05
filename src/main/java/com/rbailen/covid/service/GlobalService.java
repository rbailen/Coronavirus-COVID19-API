package com.rbailen.covid.service;

import com.rbailen.covid.service.dto.output.GlobalODTO;

/**
 * The Interface GlobalService.
 */
public interface GlobalService {

  /**
   * Gets the world total.
   *
   * @return the world total
   */
  GlobalODTO getWorldTotal();

}
