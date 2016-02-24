/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.language = 'zh-cn'; //配置语言
	//添加中文字体
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;华文中宋/华文中宋;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;
	
	config.uiColor = '#ABC5E0'; //背景颜色
	//config.width = 900;
	config.height = 300;
	//高度    // config.skin = 'v2';
	//编辑器皮肤样式    // 取消 “拖拽以改变尺寸”功能
	// config.resize_enabled = false;
	// 使用基础工具栏
	//config.toolbar = "Basic";
	// 使用全能工具栏
	//config.toolbar = "Full";
	//使用自定义工具栏
	 config.toolbar =  
         [  
         ['Source', 'Preview', '-'],  
         ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', ],  
         ['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat'],  
         ['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar','PageBreak','Link', 'Unlink', 'Anchor'],  
         ['Maximize', 'ShowBlocks'],
         '/',  
         ['Format', 'Font', 'FontSize'],  
         ['TextColor', 'BGColor'],  
         ['Bold', 'Italic', 'Underline', '-', 'Subscript', 'Superscript'],  
         ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'],  
         ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']
         ];
	// 在 CKEditor 中集成 CKFinder注意 ckfinder 的路径选择要正确。    
	config.filebrowserBrowseUrl = basePath+'/ckfinder/ckfinder.html',   
	config.filebrowserImageBrowseUrl = basePath+'/ckfinder/ckfinder.html?type=Images',   
	config.filebrowserFlashBrowseUrl = basePath+'/ckfinder/ckfinder.html?type=Flash',   
	config.filebrowserUploadUrl = basePath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',   
	config.filebrowserImageUploadUrl = basePath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',   
	config.filebrowserFlashUploadUrl = basePath+'/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',   
	config.filebrowserWindowWidth = '1000',   config.filebrowserWindowHeight = '700' ;
};
