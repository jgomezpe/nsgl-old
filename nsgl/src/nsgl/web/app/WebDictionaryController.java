package nsgl.web.app;

import nsgl.app.Side;
import nsgl.app.vc.Controller;
import nsgl.service.io.FileResource;
import nsgl.type.json.Dictionary;
import nsgl.type.json.JSON;
import nsgl.web.WebPage;

public class WebDictionaryController extends Dictionary implements Controller{
	public static final String DICT = "dict";
	public static final String I18N = "i18n";
	
	protected Side side;

	@Override
	public void setSide(Side side) { this.side = side; }

	@Override
	public Side side() { return side; }

	@Override
	public void setId(String id) {}
	
	@Override
	public String id() { return DICT; }
	
	protected String read(String file){ try{ return FileResource.txt(file); }catch(Exception e){ return null; } }

	protected String file(WebPage page, String lang){ return "i18n/"+ page.pack() + '/' + lang + ".i18n"; }

	protected JSON get( WebPage page, String lang ){
		String txt = read(file(page,lang));
		if( txt != null ){
			try{ return new JSON( txt ); } catch (Exception e) { return new JSON(); }
		}else{
			String basic = "es";
			if( lang==basic ) return new JSON();
			int k = lang.lastIndexOf('-');
			if( k < 0 ){ lang = basic; }
			else{ lang = lang.substring(0,k); }
			return get(page,lang);
		}
	}

	public void i18n( WebPage page ) {
		JSON i18n = get(page, page.language());
		dict.set(I18N, i18n);
	}}
