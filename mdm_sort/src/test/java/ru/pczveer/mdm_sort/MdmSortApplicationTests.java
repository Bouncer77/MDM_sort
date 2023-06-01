package ru.pczveer.mdm_sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class MdmSortApplicationTests {

	@Test
	void readMdmUnicodeClassicTest() {
		Map<String, String> map = CommandLineRunnerImpl.readMdmUnicodeClassic("input.txt");
		Assertions.assertEquals(6, map.size());
	}

}
