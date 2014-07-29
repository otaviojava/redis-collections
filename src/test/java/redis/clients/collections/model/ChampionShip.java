package redis.clients.collections.model;

import java.util.logging.Logger;

import redis.clients.collections.ScoresPoint;

public class ChampionShip {
	
	private static final Logger LOGGER = Logger.getLogger(ChampionShip.class.getName());

	public enum ChampionShipType{
		WIN(3), LOSE(0), DRAW(1);
		
		private int point;

		ChampionShipType(int point) {
			this.point = point;
		}

		public long getPoint() {
			return point;
		}
		
	}
	
	public ChampionShipType play(TeamSoccer a, TeamSoccer b, ScoresPoint<Long> brazilianChampionShip) {
		ChampionShipType type = playResult(a, b);
		switch (type) {
			case WIN:
				brazilianChampionShip.increment(a.getName(), type.getPoint());
				LOGGER.info(a.getName() + " win to " + b.getName());
			break;
			case LOSE:
				brazilianChampionShip.increment(b.getName(), ChampionShipType.WIN.getPoint());
				LOGGER.info(a.getName() + " lose to " + b.getName());
			break;
			default:
				brazilianChampionShip.increment(a.getName(), ChampionShipType.DRAW.getPoint());
				brazilianChampionShip.increment(b.getName(), ChampionShipType.DRAW.getPoint());
				LOGGER.info(a.getName() + " draw to " + b.getName());
		}
		return type;
	}
	
	public ChampionShipType playResult(TeamSoccer a, TeamSoccer b){
		if (hasBahiaPlaying(a,b)) {
			return bahiaAlwaysWin(a);
		}
		if (hasPalmeirasPlaying(a, b)) {
			return felipaoNightmare(a);
		}
		  return howIsWinner(a, b);
	}

	private ChampionShipType felipaoNightmare(TeamSoccer a) {
		if(isPalmeiras(a)){
			return ChampionShipType.LOSE;
		} else {
			return ChampionShipType.WIN;
		}
	}
	
	private ChampionShipType bahiaAlwaysWin(TeamSoccer a) {
		if(isBahia(a)){
			return ChampionShipType.WIN;
		} else {
			return ChampionShipType.LOSE;
		}
	}

	private ChampionShipType howIsWinner(TeamSoccer a, TeamSoccer b) {
		if (a.getName().length() > b.getName().length()){
			return ChampionShipType.WIN;
		} else if (a.getName().length() > b.getName().length()){
			return ChampionShipType.LOSE;
		}
			return ChampionShipType.DRAW;
	}

	private boolean isBahia(TeamSoccer teamSoccer) {
		
		return "Bahia".equals(teamSoccer.getName());
	}

	private boolean hasBahiaPlaying(TeamSoccer a, TeamSoccer b) {
		return isBahia(a) || isBahia(b);
	}
	
	private boolean isPalmeiras(TeamSoccer teamSoccer) {
		return "Palmeiras".equals(teamSoccer.getName());
	}

	private boolean hasPalmeirasPlaying(TeamSoccer a, TeamSoccer b) {
		return isPalmeiras(a) || isPalmeiras(b);
	}
}