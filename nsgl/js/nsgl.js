//
//nsgl JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* nsgl.js
* <P>The nsgl javascript library. Every connection to javascript uses the nsgl object
*
* <P>
*
* <h3>License</h3>
*
* Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
* All rights reserved. <br>
*
* <p>Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* <ul>
* <li> Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
* <li> Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
* <li> Neither the name of the copyright owners, their employers, nor the
* names of its contributors may be used to endorse or promote products
* derived from this software without specific prior written permission.
* </ul>
* <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*
*
*
* @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
* (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
* @version 1.0
*/

console.log('Entering here')

/* ************************************* General Methods ****************************************** */
function NSGL(url_string){
	nsgl = this
	
	// Obtains the id of the object that is associated to the client component with the given id
	nsgl.srvId = function( id ){ return id.substring(2) }
	// Obtains the id of the client component that is associated to the object with the given id
	nsgl.cltId = function( id ){ return 'JS'+id }
	// Locates the client component that is associated to the object with the given id
	nsgl.find = function( id ){ return nsgl.component(nsgl.cltId(id)) }
	// Locates the client component with the given component id
	nsgl.component = function( cltId ){ return document.getElementById(cltId) }

	nsgl.nofound = function(){ alert(nsgl.page.pack+' No Found') } 

	nsgl.parse = new NSGLParse()
	nsgl.script = new NSGLScript()
	nsgl.page = new NSGLPage(url_string)
	nsgl.html = new NSGLHTML(nsgl)
	nsgl.css = new NSGLCSS(nsgl)

	if( page.query.mode=='server' ) nsgl.server = new NSGLWebServer(nsgl)
	else nsgl.server = new NSGLJSServer(nsgl)
}

/* ************************************* Page Information ****************************************** */
function NSGLHost(www, subdomain, domain, type, country){
	var host=this
	host.www = www
	host.subdomain = subdomain
	host.domain = domain
	host.type = type
	host.country = country
	
	function addC( value ){ return value.length>0?value+'.':'' }
	function cAdd( value ){ return value.length>0?'.'+value:'' }
	host.getDomain = function(){ return addC(host.www)+addC(host.subdomain)+host.domain+cAdd(host.type)+cAdd(host.country) }
}

function NSGLURL(url){
	page = this
	page.url = url

	// Obtaining the query
	var k = url.indexOf('?')
	page.query={}
	if( k>=0 ){
		var query = url.substring( k+1 )
		url = url.substring( 0, k )
		query = query.split('&');
		for( i=0; i<query.length; i++ ) {
			split = query[i].split('=')
			page.query[split[0]] = split[1]
		}
	}

	// Determining the requested html file
	if( url.endsWith('/') ) url = url.substring(0,url.length-1)
	if( url.endsWith('.html') ){
		k = url.lastIndexOf('/')
		page.file = url.substring(k+1,url.length)
		url = url.substring(0,k)
	}else page.file = 'index.html'	
	
	// Determining the protocol
	k = url.indexOf('://')
	if( k>=0 ){
		page.protocol = url.substring(0,k)
		url = url.substring(k+3)
	}else page.protocol='http'

	// Determining the port
	k = url.lastIndexOf(':')
	if( k >= 0 ){
		page.port = url.substring(k+1, url.length)
		url = url.substring(0,k)
	}else page.port = ''

	// Getting domain type and country
	page.host = {}
	match = url.match(/(www[0-9]?\.)?/i)
	if (match != null && url.startsWith(match[0])) {
		www = match[0].substring(0,match[0].length-1)
		url = url.substring(match[0].length);
	}else www = ''

	function get( domain, type ){
		ptype = '.'+type
		var k = domain.lastIndexOf(ptype)
		if( k >= 0 ){
			if( k+ptype.length==domain.length ) return [domain.substring(0,k),type,'']
			if( domain.charAt(k+ptype.length)=='.' ) return [domain.substring(0,k),type,domain.substring(k+ptype.length+1)]
		}
		return null
	}
	var types = ['com', 'edu', 'net', 'org', 'gov', 'mil']
	var i=0
	while( i<types.length && (divide = get( url, types[i] )) == null ){ i++ }
	if( divide==null ) divide = [url, '', '']
	url = divide[0]
	var type = divide[1]
	var country = divide[2]

	// Domain and subdomain
	k = url.lastIndexOf('.')
	if( k>0 ){
		domain = url.substring(k+1)
		subdomain = url.substring(0,k)
	}else{
		domain = url
		subdomain = '' //'nsgl'
	}
	page.host = new NSGLHost(www, subdomain, domain, type, country )

	page.server = function(){ return page.protocol+'://'+page.host.getDomain()+(page.port.length>0?':'+page.port:'')+'/' }
}

function NSGLPage(url){
	page = this
	NSGLURL.call(page,url)
	page.pack = (page.host.subdomain!=null&&page.host.subdomain!='')?page.host.subdomain:'main' 
	
	if( page.query.mode==null ) page.query.mode='server'
	if( page.query.lang==null ) page.query.lang = navigator.language || navigator.userLanguage
}

/* ************************************* Client Manager ****************************************** */
function NSGLHTML(nsgl){
	html = this

	html.node = function(htmlString) {
		var div = document.createElement('div');
		div.innerHTML = htmlString.trim();
		return div.firstChild; 
	}

	html.onload = function( c ){}

	html.update = function( htmlString ){
		function callback(htmlI18N){
			var n = html.node(htmlI18N)
			function css_done(){
				var c = nsgl.component(n.id)
				var p = c.parentElement
				p.replaceChild(n,c)
				html.onload( n )			
			}
			nsgl.css.load( n, css_done )
		}
		nsgl.server.i18n(htmlString, callback)
	}

	html.gui = function(htmlTxt){ return html.update('<div id="JSmain" css="main" >'+htmlTxt+'</div>') }		

	document.body.innerHTML +='<div id="JSwindow" style = "position:absolute;width:99%;height:99%"> <div id="JSmain" ></div> </div>'
}

function NSGLCSS(nsgl){
	css = this
	css.loaded = []
	css.load = function( c, callback ){
		if(c==null||c.getAttribute==null||c.children.length==0){
			callback() 
			return
		}
		var m = 0
		var attr = c.getAttribute('css')
		var opt=null
		if( attr != null ){
			opt = attr.split(' ')
			m = opt.length
		}
		var n = c.children.length
		var total = n+m
		if(total==0){
			callback()
			return
		}
		var count = 0
		function innerBack( cssTxt ){
			count++
			css.fromTxt(cssTxt)
			if( count==total ) callback()
		}
		function childBack(){
			count++
			if( count==total ) callback()
		}
		for( var k=0; k<m; k++ ){
			if( vector.index(css.loaded, opt[k] ) == css.loaded.length ){
				css.loaded.push(attr)
				nsgl.server.css(attr, innerBack )
			}else{ innerBack(null) }
		}
		for( var i=0; i<c.children.length; i++ ) css.load(c.children[i], childBack)
	}


	css.fromTxt = function ( cssTxt ){
		if( cssTxt != null ){
			var d = document.createElement('style')
			d.innerHTML = cssTxt
			var b = document.getElementsByTagName('head')[0]
			b.appendChild(d)
		}
	}

}

/* ************************************* Script Managment ****************************************** */
function NSGLScript(){
	var script = this

	script.set = []
	script.src = []

	script.cloud = function ( type, src, callback, errorback ){
		var index = vector.index(script.src, src)
		if( index == script.src.length ){ 
			var element = document.createElement( 'script' )
			if( type!=null ) element.type = type
			element.async = true
			element.defer = true
			element.src = src 
			element.charset="utf-8"
			element.onreadystatechange = null

			var myScript = {state:0, queue:[callback] } 
			script.set.push(myScript)
			script.src.push(src)	
			
			function onload_callback(){
				myScript.state = 1
				var queue = myScript.queue
				while( queue.length>0 ){
					var f = queue[0]
					if( f!=undefined && f!=null ) f()
					queue.shift()
				} 
			}

			function onerror_callback(){
				myScript.state = 2
				if( errorback!=null ) errorback()
			}

			element.onload = onload_callback
			element.onerror = onerror_callback	
			var b = document.getElementsByTagName('script')[0]
			b.parentNode.insertBefore(element, b)
		}else{
			myScript = script.set[index]
			var state = myScript.state
			var queue = myScript.queue
			if( state==1 ) callback()
			if( state==2 ) errorback()
			if( state==0 ) queue.push( callback )
		}
	}

	script.parse = function( type, id, src, callback ){
		var index = vector.index(script.src, id)
		if( index == script.src.length ){ 
			element = document.createElement( 'script' )
			element.defer = true
			element.charset="utf-8"
			if( type!=null ) element.type = type
			script.src.push(id)
			element.innerHTML = src
			var b = document.getElementsByTagName('script')[0]
			b.parentNode.insertBefore(element, b)
		}
		if( callback != null ) callback()
	}

	script.cloudJS = function ( src, callback, errorback ){ script.cloud( 'text/javascript', src, callback, errorback ) }

	script.link = function( src, rel ){
		var link = document.createElement('link')
		link.rel = rel
		link.href = src
		link.crossorigin="anonymous"
		document.getElementsByTagName('head')[0].appendChild(link)
	}

	script.stylesheet = function( src ){ script.link( src, "stylesheet" ) }
}

/* ************************************* Parsing Methods ****************************************** */
function NSGLParse(){
	var parse = this
	parse.txt = function( str ){ return str }
	parse.xml = function ( str ){ return new DOMParser().parseFromString(str,"text/xml") },
	parse.json = function ( str ){ return ((str!=null)&&(str.length>0))?JSON.parse(str):null }
	parse.translate = function( str, dictionary, c ){
		var x = str.split(c)
		var state = 0
		var res = ""
	  	var tag = ''
		for( var i = 0; i<x.length; i++ ){
			switch( state ){
				case  0:
					res += x[i]
					state = 1
				break;    
				case 1:
		        		if( x[i].length > 0 ){
		        			tag = x[i]
						state = 2
					}else{
						res += c
		        			state = 0
					}
				break;
			    	case 2:
				    	if( x[i].length > 0 || i==x.length-1 ){
						res += ((dictionary[tag]!=null)?dictionary[tag]:tag) + x[i]
						state = 1
					}else{
		            			tag += c
						state = 3
					}                
				break;
				case 3:
		        		if( x[i].length > 0 ) tag += x[i]
						state = 2
			}
		}
		return res
	}
	parse.tags = function( str, c ){
		var array = []
		var x = str.split(c)
		var state = 0
	  	var tag = ''
		for( var i = 0; i<x.length; i++ ){
			switch( state ){
				case  0:
					state = 1
				break;    
				case 1:
		        		if( x[i].length > 0 ){
		        			tag = x[i]
						state = 2
					}else{ state = 0 }
				break;
			    	case 2:
				    	if( x[i].length > 0 || i==x.length-1 ){
						array.push(tag)
						state = 1
					}else{
		            			tag += c
						state = 3
					}                
				break;
				case 3:
		        		if( x[i].length > 0 ) tag += x[i]
					state = 2
			}
		}
		return array
	}
	parse.encode = function ( str ){ return '"' + str.replace(/\\/g, '\\\\').replace(/"/g,'\\"') +'"' }

}

/* ************************************* Abstract Server ****************************************** */
function NSGLServer( nsgl ){
	server = this

	// These function must be implemented by any concrete subclass of NSGLServer
	server.resource = function( file, callback ){}

	server.dictionary = function( dict, tags, callback ){ callback({}) }

	server.script = function( type, file, callback ){ 
		function inner(code){ 
			nsgl.script.parse( type, file, code, callback )
			//if( callback != null ) callback()
		}
		server.resource( file, inner ) 
	} 

	server.css = function( name, callback ){ server.resource( name+'.css', callback ) }
	server.html = function( name, callback ){ server.resource( name+'.html', callback ) }
	server.json = function( file, callback ){ server.resource( file, callback ) }
	server.js = function( name, callback ){ server.script( 'text/javascript', name+'.js', callback ) } 
	server.i18n = function( str, callback ){
		var parse = nsgl.parse
		var tags = parse.tags(str,'|')
		if( tags!=null & tags.length>0 ) server.dictionary( 'i18n', tags, function (dict){ callback( parse.translate(str, dict, '|') ) } )
		else callback(str)
	}
}

/* ************************************* Web Server ****************************************** */
function NSGLCommand( object, method, arg ){
	command = this
	command.object = object
	command.method = method
	command.arg = arg!=null?arg:''
}

/* ************************************* Web Servlet ****************************************** */
function NSGLServlet( page, id ){
	var servlet = this
	servlet.id = id
	
	servlet.run = function(command, callback){
		var xhttp = new XMLHttpRequest()
		xhttp.onreadystatechange = function (){
			if (xhttp.readyState==4 && xhttp.status == 200){
				trace('[Servlet.....]'+xhttp.responseType)
						//xhttp.responseType = xhttp.getResponseHeader('Content-Type')
				trace('[Servlet...response]'+xhttp.response)
				var header = xhttp.getResponseHeader('extra')
				trace('[Servlet.. header]'+header)
				if( header != null ) callback(xhttp.response, header)
				else callback(xhttp.response)
			}
		}
		var url = nsgl.page.server()
		xhttp.open('POST', url + servlet.id , true)
		xhttp.setRequestHeader("Cache-Control", "max-age=0")
		xhttp.setRequestHeader("navigator", navigator.appName)
		xhttp.setRequestHeader("platform", page.pack)
		xhttp.setRequestHeader("object", command.object)
		xhttp.setRequestHeader("method", command.method)
		xhttp.send(command.arg)
	}
	servlet.pull_cmd = new NSGLCommand('server', 'pull')
	servlet.pull = function(){ servlet.run(servlet.pull_cmd, eval) }
}

function NSGLWebServer(nsgl){
	server = this
	NSGLServer.call(server, nsgl)
	
	var page = nsgl.page
	
// @TODO: server.timer = new Timer( server )

	server.servlet = {}
	server.add = function( servlet ){ server.servlet[servlet.id] = servlet }
	server.pull = function (){ for (var s in server.servlet) server.servlet[s].pull()  }

	server.dictionary = function(dict, tags, callback){ 
		server.nsgl(new NSGLCommand('dictionary', dict, nsgl.parse.encode(JSON.stringify(tags))), function( txt ){ callback(nsgl.parse.json(txt)) } ) 
	}

	server.nsgl = function( command, callback ){ server.run('nsgl', command, callback ) }

	server.run = function ( servlet, command, callback ){ server.servlet[servlet].run(command, callback) }

	server.resource = function ( file, callback ){ 
		server.nsgl(new NSGLCommand('resource', 'download', nsgl.parse.encode(file)), callback ) 
	}

	servlet = new NSGLServlet(page, 'nsgl')
	server.add(servlet)

	server.nsgl(new NSGLCommand('server', 'init', nsgl.parse.encode(JSON.stringify(page))), eval )		
}

/* ************************************* JS Server ****************************************** */
function NSGLJSServer( nsgl ){
	var server = this
	NSGLServer.call(server, nsgl)
	server.resource = function ( file, callback ){
		if( file==null ) return;

		function inner( pack, file, callback, check ){
			var xhttp = new XMLHttpRequest()
			xhttp.onreadystatechange = function (){ 
				if (xhttp.readyState==4 ){
					var response = ""
					if( xhttp.status == 200 ){
						response = xhttp.responseText
						response = response.trim()
						if( callback != null ) callback(response)
					}else{ 
						if( check && pack != 'lib' ) inner( 'lib', file, callback, false )
						else if( callback!= null ) callback("")
					}
				}
			}
			xhttp.open('GET', pack+'/'+file, true)
			xhttp.setRequestHeader("Cache-Control", "max-age=0")
			xhttp.send()
		}
		inner( nsgl.page.pack, file, callback, true )
	}

	server.css = function( name, callback ){ server.resource( 'css/'+name+'.css', callback ) }
	server.html = function( name, callback ){ server.resource( 'html/'+name+'.html', callback ) }
	server.json = function( file, callback ){ server.resource( file, function( txt ){ callback(nsgl.parse.json(txt)) } ) }
	server.js = function( name, callback ){ server.script( 'text/javascript', 'js/'+name+'.js', callback ) } 

	server.dictionary = function( dict, tags, callback ){ 
		if( server.dict == null ) server.dict = {}

		function process( d ){
			var obj = {}
			for( var i=0; i<tags.length; i++ ){ obj[tags[i]] = d[tags[i]] }
			callback(obj)
		}

		var lang = nsgl.page.query.lang
		function i18n( json ){
			if( json != null ){
				server.dict[dict] = json
				process( server.dict[dict] )
			}else{
				var basic = 'es'
				if( lang!=basic ){
					k = lang.lastIndexOf('-')
					if( k < 0 ) lang = basic
					else lang = lang.substring(0,k)
					server.json( 'i18n/'+lang+'.i18n', i18n )
				}else callback({})
			}
		}

		function other( json ){
			if( json != null ){
				server.dict[dict] = json
				process( server.dict[dict] )
			}else callback({})
		}

		if( dict=='i18n' ){
			if( server.dict[dict] == null ) server.json( 'i18n/'+lang+'.i18n', i18n )
			else process(server.dict[dict])
		}else{
			if( server.dict[dict] == null ) server.json( 'dict/'+dict, other )
			else process(server.dict[dict])
		}
	}

	// Init the JSServer 
	server.js('main')
}

/* ************************************* Auxiliar Methods ****************************************** */
function trace( data ){ console.log( data ); }

var vector = {
	apply: function ( v, f ){
		while( v.length>0 ){
			var x = v[0]
			v.shift()
			f(x)
		}
	},

	index : function ( v, x ){
		var i=0;
		while(i<v.length &&x!=v[i]){ i++ }
		return i
	},

	id: function( n ){
		var index = []
		for( var i=0; i<n; i++ ) index.push(i)
		return index
	},

	shuffle: function( n ){
		var index = vector.id(n)
		for( var i=0; i<2*index.length; i++ ){
			var k = Math.floor(Math.random() * 10)
			var t = index[i%index.length]
			index[i%index.length] = index[k]
			index[k] = t
		}
		return index
	}
}

/* ************************************* Global nsgl object ****************************************** */
function NSGLStart(){ nsgl = new NSGL(window.location.href) }

function NSGLReset(){ window.location.reload(true) }

NSGLStart()
