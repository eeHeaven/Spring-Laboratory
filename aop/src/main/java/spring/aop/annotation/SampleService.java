package spring.aop.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    public String save(String id){
        return sampleRepository.save(id);
    }
}
