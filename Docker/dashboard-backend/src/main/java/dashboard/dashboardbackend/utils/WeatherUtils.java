package dashboard.dashboardbackend.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WeatherUtils {
	
	public static Map<Integer, String> weatherVerbose = new HashMap<Integer, String>();
	
	public static String weatherToString(int weather) {
		initVerboseMap();
		return weatherVerbose.get(weather);
	}
	
	public static String degreeToEightAxis(int windDirection) {
		TreeMap<Integer, String> quadrant = new TreeMap<Integer, String>();
		quadrant.put(0, "N");
		quadrant.put(11, "NE");
		quadrant.put(56, "E");
		quadrant.put(101, "SE");
		quadrant.put(146, "S");
		quadrant.put(191, "SW");
		quadrant.put(236, "W");
		quadrant.put(281, "NW");
		quadrant.put(326, "N");
		
		String dirWin = quadrant.lowerEntry(windDirection).getValue();
		return dirWin;
	}
	
	private static void initVerboseMap() {
		weatherVerbose.put(0,"Soleil");
		weatherVerbose.put(1,"Peu nuageux");
		weatherVerbose.put(2,"Ciel voilé");
		weatherVerbose.put(3,"Nuageux");
		weatherVerbose.put(4,"Très nuageux");
		weatherVerbose.put(5,"Couvert");
		weatherVerbose.put(6,"Brouillard");
		weatherVerbose.put(7,"Brouillard givrant");
		weatherVerbose.put(10,"Pluie faible");
		weatherVerbose.put(11,"Pluie modérée");
		weatherVerbose.put(12,"Pluie forte");
		weatherVerbose.put(13,"Pluie faible verglaçante");
		weatherVerbose.put(14,"Pluie modérée verglaçante");
		weatherVerbose.put(15,"Pluie forte verglaçante");
		weatherVerbose.put(16,"Bruine");
		weatherVerbose.put(20,"Neige faible");
		weatherVerbose.put(21,"Neige modérée");
		weatherVerbose.put(22,"Neige forte");
		weatherVerbose.put(30,"Pluie et neige mêlées faibles");
		weatherVerbose.put(31,"Pluie et neige mêlées modérées");
		weatherVerbose.put(32,"Pluie et neige mêlées fortes");
		weatherVerbose.put(40,"Averses de pluie locales et faibles");
		weatherVerbose.put(41,"Averses de pluie locales");
		weatherVerbose.put(42,"Averses locales et fortes");
		weatherVerbose.put(43,"Averses de pluie faibles");
		weatherVerbose.put(44,"Averses de pluie");
		weatherVerbose.put(45,"Averses de pluie fortes");
		weatherVerbose.put(46,"Averses de pluie faibles et fréquentes");
		weatherVerbose.put(47,"Averses de pluie fréquentes");
		weatherVerbose.put(48,"Averses de pluie fortes et fréquentes");
		weatherVerbose.put(60,"Averses de neige localisées et faibles");
		weatherVerbose.put(61,"Averses de neige localisées");
		weatherVerbose.put(62,"Averses de neige localisées et fortes");
		weatherVerbose.put(63,"Averses de neige faibles");
		weatherVerbose.put(64,"Averses de neige");
		weatherVerbose.put(65,"Averses de neige fortes");
		weatherVerbose.put(66,"Averses de neige faibles et fréquentes");
		weatherVerbose.put(67,"Averses de neige fréquentes");
		weatherVerbose.put(68,"Averses de neige fortes et fréquentes");
		weatherVerbose.put(70,"Averses de pluie et neige mêlées localisées et faibles");
		weatherVerbose.put(71,"Averses de pluie et neige mêlées localisées");
		weatherVerbose.put(72,"Averses de pluie et neige mêlées localisées et fortes");
		weatherVerbose.put(73,"Averses de pluie et neige mêlées faibles");
		weatherVerbose.put(74,"Averses de pluie et neige mêlées");
		weatherVerbose.put(75,"Averses de pluie et neige mêlées fortes");
		weatherVerbose.put(76,"Averses de pluie et neige mêlées faibles et nombreuses");
		weatherVerbose.put(77,"Averses de pluie et neige mêlées fréquentes");
		weatherVerbose.put(78,"Averses de pluie et neige mêlées fortes et fréquentes");
		weatherVerbose.put(100,"Orages faibles et locaux");
		weatherVerbose.put(101,"Orages locaux");
		weatherVerbose.put(102,"Orages fort et locaux");
		weatherVerbose.put(103,"Orages faibles");
		weatherVerbose.put(104,"Orages");
		weatherVerbose.put(105,"Orages forts");
		weatherVerbose.put(106,"Orages faibles et fréquents");
		weatherVerbose.put(107,"Orages fréquents");
		weatherVerbose.put(108,"Orages forts et fréquents");
		weatherVerbose.put(120,"Orages faibles et locaux de neige ou grésil");
		weatherVerbose.put(121,"Orages locaux de neige ou grésil");
		weatherVerbose.put(122,"Orages locaux de neige ou grésil");
		weatherVerbose.put(123,"Orages faibles de neige ou grésil");
		weatherVerbose.put(124,"Orages de neige ou grésil");
		weatherVerbose.put(125,"Orages de neige ou grésil");
		weatherVerbose.put(126,"Orages faibles et fréquents de neige ou grésil");
		weatherVerbose.put(127,"Orages fréquents de neige ou grésil");
		weatherVerbose.put(128,"Orages fréquents de neige ou grésil");
		weatherVerbose.put(130,"Orages faibles et locaux de pluie et neige mêlées ou grésil");
		weatherVerbose.put(131,"Orages locaux de pluie et neige mêlées ou grésil");
		weatherVerbose.put(132,"Orages fort et locaux de pluie et neige mêlées ou grésil");
		weatherVerbose.put(133,"Orages faibles de pluie et neige mêlées ou grésil");
		weatherVerbose.put(134,"Orages de pluie et neige mêlées ou grésil");
		weatherVerbose.put(135,"Orages forts de pluie et neige mêlées ou grésil");
		weatherVerbose.put(136,"Orages faibles et fréquents de pluie et neige mêlées ou grésil");
		weatherVerbose.put(137,"Orages fréquents de pluie et neige mêlées ou grésil");
		weatherVerbose.put(138,"Orages forts et fréquents de pluie et neige mêlées ou grésil");
		weatherVerbose.put(140,"Pluies orageuses");
		weatherVerbose.put(141,"Pluie et neige mêlées à caractère orageux");
		weatherVerbose.put(142,"Neige à caractère orageux");
		weatherVerbose.put(210,"Pluie faible intermittente");
		weatherVerbose.put(211,"Pluie modérée intermittente");
		weatherVerbose.put(212,"Pluie forte intermittente");
		weatherVerbose.put(220,"Neige faible intermittente");
		weatherVerbose.put(221,"Neige modérée intermittente");
		weatherVerbose.put(222,"Neige forte intermittente");
		weatherVerbose.put(230,"Pluie et neige mêlées");
		weatherVerbose.put(231,"Pluie et neige mêlées");
		weatherVerbose.put(232,"Pluie et neige mêlées");
		weatherVerbose.put(235,"Averses de grêle");
	}

}
