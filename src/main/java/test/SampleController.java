package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class SampleController {

	@Autowired
	private SampleRepository sampleRepository;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public Long create(@RequestParam String name) {
		Sample sample = new Sample(name);
		Sample saved = sampleRepository.save(sample);
		return saved.getId();
	}

	@RequestMapping(value = "/byname", method = RequestMethod.GET)
	@ResponseBody
	public Sample findByName(@RequestParam String name) {
		Optional<Sample> result = sampleRepository.findByName(name);
		return result.orElseThrow(() -> new NoSuchElementException());
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Sample> findAll() {
		return sampleRepository.findAll();
	}

	@RequestMapping(value = "/ids", method = RequestMethod.GET)
	@ResponseBody
	public List<Long> findAllIds() {
		List<Long> ids = sampleRepository.findAllIds();
		return ids;
	}

	@RequestMapping(value = "/idsnative", method = RequestMethod.GET)
	@ResponseBody
	public List<Long> findAllIdsNative() {
		List<Long> ids = sampleRepository.findAllIdsNative();
		for (Number id : ids) {
			System.out.println(id + " - " + id.getClass());
		}
		return ids;
	}

	@RequestMapping(value = "/byid", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam Long id) {
		sampleRepository.delete(id);
	}

	@RequestMapping(value = "/byname", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam String name) {
		Optional<Sample> result = sampleRepository.findByName(name);
		if (result.isPresent()) {
			sampleRepository.delete(result.get());
		}
	}
}