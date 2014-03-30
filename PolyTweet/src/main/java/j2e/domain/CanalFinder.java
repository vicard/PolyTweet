package j2e.domain;

import java.util.Set;

import j2e.entities.Canal;

public interface CanalFinder {

	public Set<Canal> findCanalByProprietaire(String login);
	public Canal findCanalByTag(String tag);
	//public Set<Canal> findAllCanal();

}
