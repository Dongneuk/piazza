package teamJCI.sprout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamJCI.sprout.domain.Content;
import teamJCI.sprout.domain.VisibleStatus;
import teamJCI.sprout.repository.CategoryRepository;
import teamJCI.sprout.repository.ContentRepository;
import teamJCI.sprout.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    /**
     * 글 등록
     */
    public Long register(Content content) {
        contentRepository.save(content);
        return content.getId();
    }

    /**
     * 글 수정
     */
    public Long update(Long id, String newText, VisibleStatus newStatus) {

        Content updateContent = contentRepository.findById(id);
        updateContent.setText(newText);
        updateContent.setStatus(newStatus);
        updateContent.setUploadDate(LocalDateTime.now());

        return updateContent.getId();
    }

    /**
     * 비밀번호 체크
     */
    public boolean checkPassword(Long id, String password) {
        return (contentRepository.findById(id).getUser().getPassword().equals(password));
    }

    public Content findById(Long contentId) {
        return contentRepository.findById(contentId);
    }

    public List<Content> findListC(Long categoryId) {
        return contentRepository.findByCID(categoryId);
    }

    public List<Content> findListU(Long userId) {
        return contentRepository.findByUID(userId);
    }

    public String findNameC(Long categoryId) {
        return categoryRepository.findById(categoryId).getName();
    }

    public String findNameU(Long userId) {
        return userRepository.findById(userId).getUsername();
    }


}
