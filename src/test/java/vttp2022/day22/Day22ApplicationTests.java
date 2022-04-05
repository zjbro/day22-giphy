package vttp2022.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.day22.service.GiphyService;

@SpringBootTest
class Day22ApplicationTests {

	@Autowired
	private GiphyService gService;

	@Test
	void shouldLoad10Images(){
		List<String> gifs = gService.getGiphs("dog");
		assertEquals(10, gifs.size(),"Default returns 5 search results");
	}

}
