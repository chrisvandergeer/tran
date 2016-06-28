package nl.cge.tran.persistence;

import nl.cge.tran.domein.TaggedQuery;

public class TaggedQueryDao extends EntityDao<TaggedQuery> {
	
	public static EntityDao<TaggedQuery> instance() {
		return new TaggedQueryDao();
	}

	@Override
	protected String getDatabaseFilename() {
		return "taggedQuery";
	}

}
