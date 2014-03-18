package j2e.domain;

import java.util.List;

import j2e.entities.Canal;

public interface CanalFinder {

	public Canal findCanalByTag(String tag);
	public List<Canal> findAllCanal();

}
