package study.project.domain.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.project.domain.item.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemServiceVer1 {

    private final ItemRepository itemRepository;
}
