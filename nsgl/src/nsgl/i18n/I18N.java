package nsgl.i18n;

import nsgl.service.io.FileResource;
import nsgl.type.collection.Collection;
import nsgl.type.json.JSON;

public class I18N{
	public static final String MSG = "msg";
	public static final String i18n = "i18n";
	public static final String USES = "uses";
	public static final String PACK = "pack";
	
	protected JSON dict;
	
	protected String read(String file){ try{ return FileResource.txt(file); }catch(Exception e){ return null; } }

	protected String file(String pack, String lang){ return i18n + '/' + pack + '/' + lang + '.' + i18n; }

	protected JSON get( String pack, String lang ){
		String txt = read(file(pack,lang));
		if( txt != null ){
			try{ return new JSON( txt ); } catch (Exception e) { return new JSON(); }
		}else{
			String basic = "es";
			if( lang==basic ) return new JSON();
			int k = lang.lastIndexOf('-');
			if( k < 0 ){ lang = basic; }
			else{ lang = lang.substring(0,k); }
			return get(pack,lang);
		}
	}

	public I18N( String pack, String lang ){ dict = get( pack, lang ); }

	public I18N( JSON dict ){ this.dict = dict; }

	public I18N(){ dict = new JSON(); }
	
	public String get( String key ){
		String obj = dict.getString(key);
		if( obj!=null ) return obj;
		return key;
	}

	public JSON get( Collection<String> keys ){
		JSON ndict = new JSON();
		for( String k:keys ) ndict.set(k, dict.get(k));
		return ndict;
	}

	protected static I18N lang = new I18N();
	
	public static void use( String pack, String language ){ lang = new I18N(pack, language); }
	public static void use( JSON dict ){ lang = new I18N(dict); }
	public static String translate( String word ){ return lang.get(word); }
	public static JSON translate( Collection<String> words ){ return lang.get(words); }
}