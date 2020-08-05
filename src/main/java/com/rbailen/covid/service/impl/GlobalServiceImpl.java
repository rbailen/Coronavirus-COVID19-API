package com.rbailen.covid.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rbailen.covid.model.Global;
import com.rbailen.covid.repository.GlobalRepository;
import com.rbailen.covid.service.DataService;
import com.rbailen.covid.service.GlobalService;
import com.rbailen.covid.service.dto.input.GlobalIDTO;
import com.rbailen.covid.service.dto.output.GlobalODTO;
import com.rbailen.covid.service.transformer.CovidServiceTransformer;

/**
 * The Class GlobalServiceImpl.
 */
@Service
public class GlobalServiceImpl implements GlobalService {

  /** The data service. */
  @Autowired
  private DataService dataService;

  /** The global repository. */
  @Autowired
  private GlobalRepository globalRepository;

  /** The covid service transformer. */
  @Autowired
  private CovidServiceTransformer covidServiceTransformer;

  /**
   * Gets the world total.
   *
   * @return the world total
   */
  @Override
  public GlobalODTO getWorldTotal() {
    globalRepository.deleteAll();

    GlobalODTO globalODTO = dataService.getWorldTotalFromAPI();
    GlobalIDTO globalIDTO = covidServiceTransformer.toGlobalIDTO(globalODTO);
    this.saveWorldTotal(globalIDTO);

    Iterable<Global> globalIterable = globalRepository.findAll();

    List<Global> globalList = new ArrayList<>();
    globalIterable.forEach(globalList::add);
    return globalList.stream().map(global -> covidServiceTransformer.toGlobalODTO(global))
        .collect(Collectors.toList()).stream().findFirst().orElse(null);
  }

  /**
   * Save world total.
   *
   * @param globalIDTO the global IDTO
   */
  private void saveWorldTotal(GlobalIDTO globalIDTO) {
    Global global = covidServiceTransformer.toGlobal(globalIDTO);
    globalRepository.save(global);
  }

}
