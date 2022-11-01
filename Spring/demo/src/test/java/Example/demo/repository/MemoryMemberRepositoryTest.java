package Example.demo.repository;

import Example.demo.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // System.out.println("result = " + (result == member));

        /* JUnit Test: 같은지 확인 (기댓값, 실제값) */
        // Assertions.assertEquals(member, result);
        // Assertions.assertEquals(member, null);

        /* assertj Test : import static 이용해서 구문 간단 */
        assertThat(member).isEqualTo(result);
        //assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName() {
        Member mem1 = new Member();
        mem1.setName("spring1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        repository.save(mem2);

        Member result = repository.findByName(mem1.getName()).get();

        assertThat(result).isEqualTo(mem1);
    }

    @Test
    public void findAll() {
        Member mem1 = new Member();
        mem1.setName("spring1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("spring2");
        repository.save(mem2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
