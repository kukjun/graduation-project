package io.wisoft.testermatchingplatform.service.quest;

import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import io.wisoft.testermatchingplatform.domain.task.TaskRepository;
import io.wisoft.testermatchingplatform.handler.exception.quest.QuestNotFoundException;
import io.wisoft.testermatchingplatform.web.dto.resp.quest.QuestInfoResponse;
import io.wisoft.testermatchingplatform.web.dto.resp.quest.QuestSimpleInfoResponse;
import io.wisoft.testermatchingplatform.web.dto.resp.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestFoundService {
    private final QuestRepository questRepository;
    private final TaskRepository taskRepository;

    @Transactional
    // 전체 조회
    public Page<QuestSimpleInfoResponse> all() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0,10).withSort(sort);
        return questRepository.findAll(pageable).
                map(QuestSimpleInfoResponse::from);
    }

    @Transactional
    // 카테고리 조회
    public Page<QuestSimpleInfoResponse> findByCategoryId(final Long categoryId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0,10).withSort(sort);
        return questRepository.findByCategoryId(categoryId,pageable).
                map(QuestSimpleInfoResponse::from);
    }

    @Transactional
    // Qm으로 조회
    public Page<QuestSimpleInfoResponse> findByQuestMakerId(final Long questMakerId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0,10).withSort(sort);
        return questRepository.findByQuestMakerId(questMakerId,pageable).
                map(QuestSimpleInfoResponse::from);
    }

    // 하나만 조회
    @Transactional
    public QuestInfoResponse findById(final Long id) {
        return QuestInfoResponse.from
                (questRepository.findById(id).orElseThrow(
                        () -> new QuestNotFoundException("quest not found")
                ), taskRepository.findByQuest_Id(id).
                                stream().
                                map(TaskResponse::from).
                                collect(Collectors.toList()));
    }
}