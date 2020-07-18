package engine.service;

import engine.model.Completion;
import engine.repository.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompletionService {
    @Autowired
    private CompletionRepository completionRepository;

    public Page<Completion> getPage(int pageNum, String userEmail) {
        Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("completedAt").descending());
        return completionRepository.findAllByUserEmail(userEmail, pageable);
    }
}
