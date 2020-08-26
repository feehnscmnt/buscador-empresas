function alertEmpresaSalva() {
	limparCampos();
	swal("Empresa cadastrada com sucesso!", "", "success");
}

function alertEmpresaExiste() {
	limparCampos();
	swal("A empresa informada já consta cadastrada!", "", "error");
}

function mascaraCnpj(obj, fun) {
	objeto = obj;
	funcao = fun;
	setTimeout('execMascara()', 1)
}

function execMascara() {
	objeto.value = funcao(objeto.value);
}

function cnpj(value) {
	value = value.replace(/\D/g, "");
	value = value.replace(/^(\d{2})(\d)/, "$1.$2")
	value = value.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3")
	value = value.replace(/\.(\d{3})(\d)/, ".$1/$2")
	value = value.replace(/(\d{4})(\d)/, "$1-$2")
	return value;
}

function consultarEmpresa() {
	var cnpj = document.getElementById('frm:cnpj').value.replace(/\D/g, '');
	if (cnpj == "") {
		document.getElementById('frm:cnpj').focus();
		swal("Informe um CNPJ válido!", "", "error");
	} else if (!validarCnpj(cnpj) == true) {
		document.getElementById('frm:cnpj').focus();
		swal("O CNPJ informado não é válido!", "", "error");
	} else {
		document.getElementById('status').innerHTML = "<div class=\"alert alert-info\">Buscando CNPJ...</div>";
		$.ajax({
			url: 'https://www.receitaws.com.br/v1/cnpj/' + cnpj,
			method: 'GET',
			dataType: 'jsonp',
			jsonCallback: 'jsonp',
			complete: function(xhr) {
				var response = xhr.responseJSON;
				if (response.status == 'OK') {
					document.getElementById('frm:nome').value = response.nome;
					document.getElementById('frm:endereco').value = response.logradouro;
					document.getElementById('frm:numero').value = response.numero;
					document.getElementById('frm:complemento').value = response.complemento;
					document.getElementById('frm:municipio').value = response.municipio;
					document.getElementById('frm:uf').value = response.uf;
					document.getElementById('frm:cep').value = response.cep;
					document.getElementById('frm:telefone').value = response.telefone;
					document.getElementById('frm:email').value = response.email;
					document.getElementById('frm:cnpj').focus();
					document.getElementById('status').innerHTML = "<div class=\"alert alert-success\">O CNPJ foi encontrado!</div>";
				} else {
					alert(response.message);
				}
			}
		});
	}
}

function validarCnpj(cnpj) {
	if (cnpj == "") return false;
	if (cnpj.length != 14) return false;
	if (cnpj == "00000000000000" || 
	    cnpj == "11111111111111" || 
	    cnpj == "22222222222222" || 
	    cnpj == "33333333333333" || 
	    cnpj == "44444444444444" || 
	    cnpj == "55555555555555" || 
	    cnpj == "66666666666666" || 
	    cnpj == "77777777777777" || 
	    cnpj == "88888888888888" || 
	    cnpj == "99999999999999") return false;
	var tamanho = cnpj.length - 2
    var numeros = cnpj.substring(0, tamanho);
    var digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;
         
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
          return false;
    return true;
}

function limparCampos() {
	document.getElementById('frm:cnpj').value = "";
	document.getElementById('frm:nome').value = "";
	document.getElementById('frm:endereco').value = "";
	document.getElementById('frm:numero').value = "";
	document.getElementById('frm:complemento').value = "";
	document.getElementById('frm:municipio').value = "";
	document.getElementById('frm:uf').value = "";
	document.getElementById('frm:cep').value = "";
	document.getElementById('frm:telefone').value = "";
	document.getElementById('frm:email').value = "";
	document.getElementById('status').innerHTML = "<div></div>";
	document.getElementById('frm:cnpj').focus();
}