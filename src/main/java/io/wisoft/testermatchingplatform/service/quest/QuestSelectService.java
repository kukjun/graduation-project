package io.wisoft.testermatchingplatform.service.quest;

import io.wisoft.testermatchingplatform.domain.quest.QuestRepository;
import io.wisoft.testermatchingplatform.domain.task.TaskRepository;
import io.wisoft.testermatchingplatform.web.dto.resp.quest.QuestInfoResponse;
import io.wisoft.testermatchingplatform.web.dto.resp.quest.QuestSimpleInfoResponse;
import io.wisoft.testermatchingplatform.web.dto.resp.task.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestSelectService {
    private final QuestRepository questRepository;
    private final TaskRepository taskRepository;

    @Transactional
    // 전체 조회
    public List<QuestSimpleInfoResponse> all() {
        return questRepository.
                findAll().
                stream().
                map(QuestEntity -> QuestSimpleInfoResponse.from(QuestEntity.toDomain())).
                collect(Collectors.toList());
    }

    @Transactional
    // 카테고리 조회
    public List<QuestSimpleInfoResponse> findByCategoryId(final Long categoryId) {
        return questRepository.findByCategoryId(categoryId).
                stream().
                map(QuestEntity -> QuestSimpleInfoResponse.from(QuestEntity.toDomain())).
                collect(Collectors.toList());
    }

    @Transactional
    // Qm으로 조회
    public List<QuestSimpleInfoResponse> findByQuestMakerId(final Long questMakerId) {
        return questRepository.findByQuestMakerId(questMakerId).
                stream().
                map(QuestEntity -> QuestSimpleInfoResponse.from(QuestEntity.toDomain())).
                collect(Collectors.toList());
    }

    // 하나만 조회
    @Transactional
    public QuestInfoResponse findById(final Long id) {
        return QuestInfoResponse.from(questRepository.findById(id).orElseThrow().toDomain(),
                taskRepository.findByQuest_Id(id).
                stream().
                map(TaskEntity -> TaskResponse.from(TaskEntity.toDomain())).
                collect(Collectors.toList()));
    }
}