package com.kuzonchen.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class orm {

	public static void main(String args[]) throws Exception {
		//Create Schema
		Schema schema = new Schema(1, "com.kuzonchen.model");
		Entity timetable = schema.addEntity("Timetable");
		timetable.addIdProperty();
		timetable.addStringProperty("StopName");
		timetable.addStringProperty("StopTime");
		new DaoGenerator().generateAll(schema, "../app/src/main/java/com/kuzonchen/model");
	}

}
