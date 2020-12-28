package com.company.ellRes.fileService.converterHTMLDOCX.main;

import org.docx4j.org.xhtmlrenderer.render.BlockBox;
import org.docx4j.wml.ContentAccessor;

public interface DivHandler {
	
	public ContentAccessor enter(BlockBox blockBox, ContentAccessor contentContext);

	public void leave();
	
}
