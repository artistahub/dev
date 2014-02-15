var iframeDocument = frames['media-frame'].document;
$( iframeDocument ).ready(function(){
      $('#media-frame').contents().find('button #c').click( function(){
          alert('ouiii');
      });
});
